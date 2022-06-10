import {Component, OnInit} from '@angular/core';
import {UserServiceImpl} from "../../../service/entity/impl/UserServiceImpl";
import {TokenStorageService} from "../../../service/auth/token-storage.service";
import {User} from "../../../model/User";
import {Image} from "../../../model/Image";
import {ImageServiceImpl} from "../../../service/entity/impl/ImageServiceImpl";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-userpage',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  user: User;
  username: string;
  newName: string;
  newSurname: string;
  newPhone: string;
  imageSrc: string;
  role: string;
  newImageId: number;
  newUserImage: Image;
  userProfileForm: FormGroup;
  fileHolder: File | null;

  constructor(private userService: UserServiceImpl,
              private tokenStorage: TokenStorageService,
              private imageService: ImageServiceImpl,
              private fb: FormBuilder,) {
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
      // this.userProfileForm.get('userImageFile').setValue(file);
    }
  }

  onSave() {

    if (!this.userProfileForm.get('userImageFile').value.imageId) {
      const formData = new FormData();
      // formData.append('file', this.userProfileForm.get('userImageFile').value);
      formData.append('file', this.fileHolder, this.fileHolder.name);
      this.imageService.uploadImage(formData).subscribe(id => {
        console.log(id);
        this.newUserImage = new Image(id);
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
    console.log(this.user);
    this.userService.update(this.user).subscribe(res => {});
  }
}
