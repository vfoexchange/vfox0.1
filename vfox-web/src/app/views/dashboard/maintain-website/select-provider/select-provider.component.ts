import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MaintainWebsite } from '../../../../services/maintain-website.service';
import { ToastrService } from 'ngx-toastr';
import { TreeviewItem, TreeviewConfig } from 'ngx-treeview';


@Component({
  selector: 'Tree-Structure',
  templateUrl: './select-provider.component.html'
})
export class SelectProviderComponent {

 
  dropdownEnabled = true;
  items1: TreeviewItem[];
  items2: TreeviewItem[];
  values: number[];
  config = TreeviewConfig.create({
    hasAllCheckBox: false,
    hasFilter: false,
    hasCollapseExpand: false,
    decoupleChildFromParent: false,
    maxHeight: 1000
  });

 
  constructor(private maintainWebsite: MaintainWebsite) {
  }


  ngOnInit() {
    this.items1 = this.maintainWebsite.getBooks1();
    this.items2 = this.maintainWebsite.getBooks2();
  }

  onFilterChange(value: string) {
    console.log('filter:', value);
  }

}

