import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectService } from 'src/app/Services/ProjectService';
import { ProxyService } from 'src/app/Services/ProxyService';
import { SendTitleService } from 'src/app/Services/SendTitleService';
import { CookiesUtils } from 'src/app/Utils/CookiesUtils';

@Component({
  selector: 'app-showcase',
  templateUrl: './showcase.component.html',
  styleUrls: ['./showcase.component.scss']
})
export class ShowcaseComponent implements OnInit {
  isLogged!: boolean;
  projects!: {
    title: string,
    description: string,
    category: string,
    image: any,
    video: any,
    members: string[],
    amountToReach: number,
    amountReached: number,
    startDate: Date,
    endDate: Date
  }[];

  constructor(
    private cookieUtils: CookiesUtils,
    private sendTitleService: SendTitleService,
    private router: Router,
    private proxyService: ProxyService,
    private projectService: ProjectService
  ) {}

  ngOnInit(): void {
    this.isLogged = this.cookieUtils.checkLogged();
    this.projectService.getProjects().subscribe((result: any) => {
      this.projects = Object.keys(result).map((key) => {
        const project = result[key];
        return {
          title: project.title,
          description: project.description,
          category: project.category,
          image: this.convert(project.image),
          video: this.convert(project.video),
          members: project.members,
          amountToReach: project.amountToReach,
          amountReached: project.amountReached,
          startDate: project.startDate,
          endDate: project.endDate
        };
      });
    });
  }

  requestPermitAdmin(title: string) {
    this.proxyService.sendModRequest({
      username: this.cookieUtils.getUsernameFromCookie(),
      action: 'Modera',
      role: this.cookieUtils.getRoleFromCookie(),
      title: title
    });
  }

  requestPermitFinanziatore(title: string) {
    this.sendTitleService.title = title;
    this.proxyService.sendFinanziatoreRequest({
      username: this.cookieUtils.getUsernameFromCookie(),
      action: 'Finanzia',
      role: this.cookieUtils.getRoleFromCookie()
    });
  }

  convert(source: any): string {
    if (source) {
      const uint8Array = Uint8Array.from(atob(source), c => c.charCodeAt(0));

      const binaryString = String.fromCharCode.apply(null, Array.from(uint8Array));
      const converted_source = btoa(binaryString);
      return `data:image/png;base64,${converted_source}`;
    } else {
      return 'assets/immagine-di-fallback.png';
    }
  }
}
