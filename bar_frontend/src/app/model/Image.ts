export class Image {
  imageId: number;
  name: string;
  filename: string;
  contentType: string;
  size: number;
  bytes: Blob;


  constructor(imageId: number, name?: string, filename?: string, contentType?: string, size?: number, bytes?: Blob) {
    this.imageId = imageId;
    this.name = name;
    this.filename = filename;
    this.contentType = contentType;
    this.size = size;
    this.bytes = bytes;
  }
}
