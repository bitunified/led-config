export class ProductCodeComposition {
  codes: Array<string> = [];

  public constructor() {

  }

  getCode() {
    let ccode: string = "";

    for (let code of this.codes) {
      if (code) {

        ccode = ccode.concat(code);
      }
    }
    return ccode;
  }
}
