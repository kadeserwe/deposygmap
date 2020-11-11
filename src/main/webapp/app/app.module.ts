import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { SygmapSharedModule } from 'app/shared/shared.module';
import { SygmapCoreModule } from 'app/core/core.module';
import { SygmapAppRoutingModule } from './app-routing.module';
import { SygmapHomeModule } from './home/home.module';
import { SygmapEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    SygmapSharedModule,
    SygmapCoreModule,
    SygmapHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    SygmapEntityModule,
    SygmapAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class SygmapAppModule {}
