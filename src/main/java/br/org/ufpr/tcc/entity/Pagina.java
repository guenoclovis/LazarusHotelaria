package br.org.ufpr.tcc.entity;

public class Pagina {
	private int currentPage;

	private int pageSize;

	private int totalResults;

	private int totalPages;

	public Pagina() {
		pageSize = 10;
		totalResults = 0;
		reset();
	}

	private void reset() {
		currentPage = 0;
		totalPages = 0;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	private void setTotalPages(int totalPages) {
		validateNegativeValue(totalPages);
		this.totalPages = totalPages;

		if (totalPages == 0) {
			reset();
		} else if (getCurrentPage() >= totalPages) {
			setCurrentPage(totalPages - 1);
		}
	}

	private void validateNegativeValue(int input) throws IndexOutOfBoundsException {
		if (input < 0) {
			throw new IndexOutOfBoundsException("colocar mensagem");
		}
	}

	private void validateCurrentPage(int currentPage) throws IndexOutOfBoundsException {
		if (currentPage >= this.totalPages) {
			if (this.totalPages > 0) {
				throw new IndexOutOfBoundsException("colocar mensagem");
			}
		}
	}

	public void setCurrentPage(int currentPage) {
		validateNegativeValue(currentPage);
		validateCurrentPage(currentPage);
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		validateNegativeValue(totalResults);
		this.totalResults = totalResults;

		if (totalResults > 0) {
			setTotalPages();
		} else {
			reset();
		}
	}

	private void setTotalPages() {
		if (totalResults > 0) {
			setTotalPages((int) Math.ceil(totalResults * 1d / getPageSize()));
		} else {
			setTotalPages(0);
		}
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getFirstResult() {
		return getCurrentPage() * getPageSize();
	}

	public void setPageSize(int pageSize) {
		validateNegativeValue(pageSize);
		this.pageSize = pageSize;

		if (pageSize > 0) {
			setTotalPages();
		} else {
			reset();
		}
	}

	private void validateFirstResult(int firstResult) throws IndexOutOfBoundsException {
		if (firstResult >= this.totalResults) {
			if (this.totalResults > 0) {
				throw new IndexOutOfBoundsException("colocar mensagem");
			}
		}
	}

	public void setFirstResult(int firstResult) {
		validateNegativeValue(firstResult);
		validateFirstResult(firstResult);

		if (firstResult > 0) {
			setCurrentPage(firstResult / pageSize);
		} else {
			setCurrentPage(0);
		}
	}

	@Override
	public String toString() {
		return "Pagina [currentPage=" + currentPage + ", pageSize=" + pageSize + ", totalResults=" + totalResults
				+ ", totalPages=" + totalPages + "]";
	}
}
