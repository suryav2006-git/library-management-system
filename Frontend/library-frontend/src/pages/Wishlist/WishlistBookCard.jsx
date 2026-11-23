
import {
    BookmarkRemove,
    MenuBook,
} from "@mui/icons-material";

const WishlistBookCard = ({ book, onRemove }) => {
    return (
        <div className="wishlist-card">

            {/* Book Cover */}
            <div className={`wishlist-cover cover-${book.coverColor}`}>
                <MenuBook className="wishlist-cover-icon" />
                <span>{book.title}</span>
            </div>

            {/* Book Details */}
            <div className="wishlist-card-content">
                <div className="wishlist-book-info">
                    <div>
                        <h3>{book.title}</h3>
                        <p>by {book.author}</p>
                    </div>

                    <button
                        className="wishlist-remove"
                        onClick={() => onRemove(book.id)}
                        title="Remove from wishlist"
                        aria-label={`Remove ${book.title} from wishlist`}
                    >
                        <BookmarkRemove />
                    </button>
                </div>

                <div className="wishlist-card-footer">
                    <span className="wishlist-category">
                        {book.category}
                    </span>

                    <span
                        className={`wishlist-status ${
                            book.status === "AVAILABLE"
                                ? "status-available"
                                : "status-unavailable"
                        }`}
                    >
                        {book.status === "AVAILABLE"
                            ? "Available"
                            : "Unavailable"}
                    </span>
                </div>
            </div>
        </div>
    );
};

export default WishlistBookCard;
