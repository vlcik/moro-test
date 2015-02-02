package cz.moro.sokrates.dao.editor;

import java.beans.PropertyEditorSupport;

import cz.moro.sokrates.model.Book;
import cz.moro.sokrates.service.IBookService;

/**
 * Converter between String representation of ID and Book entity
 * @author Juraj Vlk
 *
 */
public class BookEditor extends PropertyEditorSupport {
	
	private IBookService service;
	
	public BookEditor(IBookService service) {
		super();
		this.service = service;
	}

	@Override
    public void setAsText(String text) throws IllegalArgumentException {
		Integer id = Integer.parseInt(text);
        Book value = id == 0 ? null : service.getBookById(id);
        setValue(value); 
    }

    @Override
    public String getAsText() {
        Book value = (Book) getValue();
        return value != null ? String.valueOf(value.getId()) : "";
    }
}
