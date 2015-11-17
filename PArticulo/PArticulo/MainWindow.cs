using System;
using Gtk;
using System.Collections;

using SerpisAd;
using PArticulo;

public partial class MainWindow: Gtk.Window
{	
	public MainWindow (): base (Gtk.WindowType.Toplevel)
	{
		Build ();
		Title="Articulo";
		Console.WriteLine ("MainWindow ctor.");
		fillTreeView ();

		newAction.Activated += delegate {
			new ArticuloView();
		};

		refreshAction.Activated += delegate {
			fillTreeView();
		};

		deleteAction.Activated += delegate{

			object id = TreeViewHelper.GetId(treeView);
			Console.WriteLine("Click en delectAction id={0}",TreeViewHelper.GetId(treeView));
			delete(id);

		};
		treeView.Selection.Changed += delegate {
			Console.WriteLine("Ha ocurrido un cambio");
			deleteAction.Sensitive= TreeViewHelper.IsSelected(treeView);
		};
		deleteAction.Sensitive = false;
		//newAction.Activated += newActionActivated;
	}

	public static bool ConfirmDelete(Window window){
		MessageDialog messageDialog = new MessageDialog (
			window,
			DialogFlags.DestroyWithParent,
			MessageType.Question,
			ButtonsType.YesNo,
			"¿Quieres eliminar el elemento seleccionado?"
			);
		messageDialog.Title = window.Title;
		ResponseType response = (ResponseType)messageDialog.Run ();
		messageDialog.Destroy ();

		return response == ResponseType.Yes;
	
	}


	private void fillTreeView(){
		QueryResult queryResult = PersisterHelper.Get ("select * from articulo");
		TreeViewHelper.Fill (treeView, queryResult);
	}

//	void newActionActivated (object sender, EventArgs e)
//	{
//		new ArticuloView ();
//	}

	protected void OnDeleteEvent (object sender, DeleteEventArgs a)
	{
		Application.Quit ();
		a.RetVal = true;
	}
	private void delete(object id){
	
		if (ConfirmDelete (this)) {
			QueryResult queryResult = PersisterHelper.Get ("DELETE FROM articulo WHERE id="+id.ToString());
			fillTreeView ();
		}
	}
}
