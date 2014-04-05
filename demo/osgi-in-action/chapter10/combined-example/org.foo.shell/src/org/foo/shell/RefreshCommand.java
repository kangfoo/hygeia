package org.foo.shell;

import java.io.PrintStream;
import java.util.*;
import org.osgi.framework.Bundle;
import org.osgi.service.packageadmin.ExportedPackage;
import org.osgi.service.packageadmin.PackageAdmin;

public class RefreshCommand extends BasicCommand {

  public void exec(String args, PrintStream out, PrintStream err) throws Exception {
    if (args == null) {
      getPackageAdminService().refreshPackages(null);
    } else {
      List<Bundle> bundles = new ArrayList<Bundle>();
      StringTokenizer tok = new StringTokenizer(args);
      while (tok.hasMoreTokens()) {
        bundles.add(getBundle(tok.nextToken()));
      }
      getPackageAdminService().refreshPackages(bundles.toArray(new Bundle[bundles.size()]));
    }
    
    PackageAdmin admin = getPackageAdminService();
    
    ExportedPackage[] exports = admin.getExportedPackages((Bundle)null);
    
    Bundle[] bundles = m_context.getBundles();
    
    for (Bundle bundle : bundles) {
    	System.out.println(bundle.getBundleId());
    	for (ExportedPackage pack : exports) {
    		Bundle[] importers = pack.getImportingBundles();
    		if (importers != null) {
    			for (Bundle importer : importers) {
    				if (importer == bundle) {
    					System.out.println(pack);
    				}
    			}
    		}
    	}
    }
    
  }

  private PackageAdmin getPackageAdminService() {
    return (PackageAdmin) m_context.getService(m_context.getServiceReference(PackageAdmin.class.getName()));
  }
}
