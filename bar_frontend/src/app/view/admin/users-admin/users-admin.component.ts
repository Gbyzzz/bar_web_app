import {Component, OnInit} from '@angular/core';
import {UserServiceImpl} from "../../../service/entity/impl/UserServiceImpl";
import {User} from "../../../model/User";
import {Sort} from "@angular/material/sort";
import {EditUserDialogComponent} from "../../dialog/edit-user-dialog/edit-user-dialog.component";
import {MatDialog} from '@angular/material/dialog';
import {DialogAction, DialogResult} from "../../dialog/DialogResult";
import {Pagination, SortDirection, SortDirectionUtil} from "../../../model/pagination/Pagination";
import {Cocktail} from "../../../model/Cocktail";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-users-admin',
  templateUrl: './users-admin.component.html',
  styleUrls: ['./users-admin.component.css']
})
export class UsersAdminComponent implements OnInit {

  readonly defaultPageSize = 10;
  readonly defaultPageNumber = 0;
  readonly defaultSortDirection = SortDirection.DESC;

  users: User[];
  sortedData: User[];

  pagination: Pagination;
  totalUsersFound: number;


  constructor(private dialog: MatDialog,
              private userService: UserServiceImpl,
              private sortDirectionUtil: SortDirectionUtil) {
    this.pagination = new Pagination(this.defaultPageSize, this.defaultPageNumber, this.defaultSortDirection);

    this.getPage();
  }

  ngOnInit(): void {
  }

  pageChanged(pageEvent: PageEvent) {

    if (this.pagination.pageSize != pageEvent.pageSize) {
      this.pagination.pageNumber = 0;
    } else {
      this.pagination.pageNumber = pageEvent.pageIndex;
    }

    this.pagination.pageSize = pageEvent.pageSize;

    this.getPage();
  }

  getPage() {
    this.userService.findAllWithPages(this.pagination).subscribe(users => {
      this.users = users.content;
      this.sortedData = users.content;
      this.totalUsersFound = users.totalElements;
    });
  }

  updateUser(user: User) {
    this.userService.update(user).subscribe();
  }

  toggleEnabled(user: User) {

    if (user.enabled) {
      user.enabled = false;
    } else {
      user.enabled = true;
    }

    this.updateUser(user);
  }

  sortData(sort: Sort) {
    if (this.totalUsersFound > this.pagination.pageSize && (sort.active == 'userId'
    || sort.active == 'regDate')) {
      if (!sort.active || sort.direction === '') {
        this.pagination.sortDirection = SortDirection.DESC;
      } else {
        this.pagination.sortDirection = this.sortDirectionUtil.change(this.pagination.sortDirection);
      }
      this.getPage();
    } else {
      const data = this.users.slice();
      if (!sort.active || sort.direction === '') {
        this.sortedData = data;
        return;
      }

      this.sortedData = data.sort((a, b) => {
        const isAsc = sort.direction === 'asc';
        switch (sort.active) {
          case 'userId':
            return compare(a.userId, b.userId, isAsc);
          case 'username':
            return compare(a.username, b.username, isAsc);
          case 'name':
            return compare(a.name, b.name, isAsc);
          case 'surname':
            return compare(a.surname, b.surname, isAsc);
          case 'role':
            return compare(a.role, b.role, isAsc);
          case 'enabled':
            return compare(a.enabled, b.enabled, isAsc);
          case 'regDate':
            return compare(a.regDate, b.regDate, isAsc);
          default:
            return 0;
        }
      });
    }
  }

    openEditDialog(user:User):void {
      const dialogRef = this.dialog.open(EditUserDialogComponent, {
        data: [user],
        autoFocus: false
      });

      dialogRef.afterClosed().subscribe(result => {

        if (!(result)) {
          return;
        }

        if (result.action === DialogAction.SAVE) {
          this.updateUser(user);
          return;
        }
      });
    }
  }

  function

  compare(a: number | string | boolean | Date,
          b: number | string | boolean | Date, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }
