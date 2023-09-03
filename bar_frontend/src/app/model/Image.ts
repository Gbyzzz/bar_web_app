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
  // toJSON(): any {
  //   const { imageId, ...rest } = this;
  //   return {
  //     '@class': 'com.gbyzzz.bar_web_app.bar_backend.dto.ImageDTO',
  //     ...rest
  //   };
  // }

  // public toJSON(): Image {
  //   return Object.assign({}, this, {
  //     '@class': 'com.gbyzzz.bar_web_app.bar_backend.dto.ImageDTO'
  //   });
  // }
}
