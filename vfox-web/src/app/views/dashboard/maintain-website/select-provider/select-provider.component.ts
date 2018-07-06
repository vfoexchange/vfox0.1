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
  items: TreeviewItem[];
  values: number[];
  config = TreeviewConfig.create({
    hasAllCheckBox: false,
    hasFilter: false,
    hasCollapseExpand: true,
    decoupleChildFromParent: false,
    maxHeight: 250
  });

 
  constructor(private maintainWebsite: MaintainWebsite) {
  }


  ngOnInit() {
    this.items = this.maintainWebsite.getBooks();
  }

  onFilterChange(value: string) {
    console.log('filter:', value);
  }

}

