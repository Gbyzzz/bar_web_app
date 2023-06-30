import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserServiceImpl} from "../../../service/entity/impl/UserServiceImpl";
import {TokenStorageService} from "../../../service/auth/token-storage.service";
import {User} from "../../../model/User";
import {Image} from "../../../model/Image";
import {ImageServiceImpl} from "../../../service/entity/impl/ImageServiceImpl";
import {UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import Validation from "../../../utils/validation";
import {AuthService} from "../../../service/auth/auth.service";
import {DialogAction} from "../../dialog/DialogResult";
import {MatDialog} from "@angular/material/dialog";
import {
  ChangePasswordDialogComponent
} from "../../dialog/change-password-dialog/change-password-dialog.component";

@Component({
  selector: 'app-userpage',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  changePasswordForm:  any = {
    oldPassword: null,
    newPassword: null
  };
  user: User;
  username: string;
  newName: string;
  newSurname: string;
  newPhone: string;
  imageSrc: string;
  role: string;
  newImageId: number;
  newUserImage: Image;
  userProfileForm: UntypedFormGroup;
  fileHolder: File | null;
  isPasswordChanged: boolean;
  @ViewChild('closeAlertButton', { static: false, read: ElementRef })
  closeAlertButton!: ElementRef<HTMLButtonElement>;
  constructor(private dialog: MatDialog,
              private userService: UserServiceImpl,
              private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private imageService: ImageServiceImpl,
              private fb: UntypedFormBuilder,) {
    this.changePasswordForm = this.fb.group(
      {
        oldPassword: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(20)
          ]
        ],
        newPassword: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(40)
          ]
        ],
        confirmPassword: ['', Validators.required]
      },
      {
        validators: [Validation.match('newPassword', 'confirmPassword')]
      }
    );

    this.userProfileForm = this.fb.group({
      userImageFile: '',
      userName: '',
      userSurname: '',
      userPhone: '',
    });

    this.role = tokenStorage.getUser().role;
    console.log(tokenStorage.getUser());
    userService.findById(tokenStorage.getUser().userId).subscribe(user => {
      console.log(user);
      this.user = user;
      this.newName = user.name;
      this.username = user.username;
      this.newSurname = user.surname;
      this.newPhone = user.phone;
      this.newImageId = user.userPic.imageId;
      this.imageSrc = imageService.getImage(this.newImageId);
    });
  }

  ngOnInit(): void {
  }

  onFileChange(event) {
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.imageSrc = reader.result as string;
      };
      this.fileHolder = event.target.files[0];
    }
  }

  onSave() {
    if (this.userProfileForm.get('userImageFile').value) {
      const formData = new FormData();
      formData.append('file', this.fileHolder, this.fileHolder.name);
      this.imageService.uploadImage(formData).subscribe(image => {
        this.newUserImage = image;
        this.user.userPic = this.newUserImage;
        this.updateUserValues();
      });
    } else {
      this.updateUserValues();
    }
  }

  updateUserValues(){
    // console.log(this.newName);
    console.log(this.user.name);

    this.user.name = this.newName;
    this.user.surname = this.newSurname;
    this.user.phone = this.newPhone;
    this.userService.update(this.user).subscribe(res => {});
  }
  openChangePasswordDialog(){


      const dialogRef = this.dialog.open(ChangePasswordDialogComponent, {
        autoFocus: false
      });

      dialogRef.afterClosed().subscribe(result => {

        if (!(result)) {
          return;
        }

        if (result.action === DialogAction.SAVE) {
          this.isPasswordChanged = true;
          setTimeout(() => {
            this.closeAlertButton.nativeElement.click()
          }, 5000);
          return;
        }
      });
  }

  protected readonly undefined = undefined;
}
