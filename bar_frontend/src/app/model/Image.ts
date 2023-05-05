import * as bytes from "bytes";

export class Image {
  private _imageId: number;
  private _name: string;
  private _filename: string;
  private _contentType: string;
  private _size: number;
  private _bytes: Blob;


  constructor(imageId: number, name?: string, filename?: string, contentType?: string, size?: number, bytes?: Blob) {
    this._imageId = imageId;
    this._name = name;
    this._filename = filename;
    this._contentType = contentType;
    this._size = size;
    this._bytes = bytes;
  }

  get imageId(): number {
    return this._imageId;
  }

  set imageId(value: number) {
    this._imageId = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get filename(): string {
    return this._filename;
  }

  set filename(value: string) {
    this._filename = value;
  }

  get contentType(): string {
    return this._contentType;
  }

  set contentType(value: string) {
    this._contentType = value;
  }

  get size(): number {
    return this._size;
  }

  set size(value: number) {
    this._size = value;
  }

  get bytes(): Blob {
    return this._bytes;
  }

  set bytes(value: Blob) {
    this._bytes = value;
  }
}
