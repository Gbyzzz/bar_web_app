import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserServiceImpl} from "../../../service/entity/impl/UserServiceImpl";
import {TokenStorageService} from "../../../service/auth/token-storage.service";
import {User} from "../../../model/User";
import {Image} from "../../../model/Image";
import {ImageServiceImpl} from "../../../service/entity/impl/ImageServiceImpl";
import {UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import Validation from "../../../utils/validation";
import {PasswordChange} from "../../../model/registration/PasswordChange";
import {AuthService} from "../../../service/auth/auth.service";
import {delay} from "rxjs";

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
  isPasswordValid: boolean;
  isOldPasswordMatch: boolean;
  isConfirmPasswordMatch: boolean;
  isNewPasswordUniqueToOld: boolean;
  isPasswordChanged: boolean;
  oldPasswordValue: string = '';
  newPasswordValue: string = '';
  confirmPasswordValue: string = '';
  @ViewChild('closeAlertButton', { static: false, read: ElementRef })
  closeAlertButton!: ElementRef<HTMLButtonElement>;
  constructor(private userService: UserServiceImpl,
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
    userService.findById(tokenStorage.getUser().userId).subscribe(user => {
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

    if (!this.userProfileForm.get('userImageFile').value.imageId) {
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
    this.user.name = this.newName;
    this.user.surname = this.newSurname;
    this.user.phone = this.newPhone;
    this.userService.update(this.user).subscribe(res => {});
  }

  onInputChange() {
    this.newPasswordValue = this.changePasswordForm
      .get('newPassword').value;

    this.oldPasswordValue = this.changePasswordForm
      .get('oldPassword').value;

    this.confirmPasswordValue = this.changePasswordForm
      .get('confirmPassword').value;

    if(this.newPasswordValue.length >= 6 ) {
      this.isPasswordValid = true;
    } else {
      this.isPasswordValid = false;
    }
    if(this.newPasswordValue != this.oldPasswordValue) {
      this.isNewPasswordUniqueToOld = true
    } else {
      this.isNewPasswordUniqueToOld = false
    }

    if(this.confirmPasswordValue == this.newPasswordValue) {
      this.isConfirmPasswordMatch = true;
    } else {
      this.isConfirmPasswordMatch = false;
    }
  }

  onOldPasswordInputChange() {
    console.log("oldPassChange")
    console.log(this.oldPasswordValue);
    this.onInputChange();
    this.authService.checkPassword(
      new PasswordChange(this.tokenStorage
        .getUser().email, this.oldPasswordValue)).subscribe(res => {
      this.isOldPasswordMatch = res;
    });
  }

  delay(ms: number): Promise<void> {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  onSubmitChangePassword(): void {
    console.log("change pass");
    this.authService.changePassword(new PasswordChange(this.tokenStorage
      .getUser().email, this.oldPasswordValue, this.newPasswordValue))
      .subscribe(res => {
        if(res){
          this.isPasswordChanged = res;
          const button = document.getElementById('close_modal');
          button.click();
          const buttonAlert = document.getElementById('close_alert');
          console.log("1");
          setTimeout(()=>{this.closeAlertButton.nativeElement.click()}, 5000);
          console.log("2");
          console.log(buttonAlert);
          console.log(buttonAlert);
        }
      });
  }

  protected readonly undefined = undefined;
}
