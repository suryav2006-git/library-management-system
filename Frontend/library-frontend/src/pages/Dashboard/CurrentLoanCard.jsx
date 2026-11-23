
import { AccessTime, ArrowForward } from "@mui/icons-material";
import { Button, Chip } from "@mui/material";
import GetStatusChip from "./GetStatusChip";

const CurrentLoanCard = ({ loan }) => {

    const isOverdue = loan.remainingDays < 0;

    const days = isOverdue
        ? loan.overdueDays
        : loan.remainingDays;

    return (
        <div className="
            group
            flex flex-col sm:flex-row
            sm:items-center
            justify-between
            gap-4
            w-full
            p-4 sm:p-5
            bg-white
            border border-gray-200
            rounded-xl
            transition-all duration-300
            hover:border-indigo-300
            hover:shadow-lg hover:shadow-indigo-100/50
        ">

            {/* Book Information */}
            <div className="flex items-center gap-4 min-w-0 flex-1">

                {/* Book Cover */}
                <div className="flex-shrink-0">

                    <img
                        src={loan.bookCoverImage}
                        alt={loan.bookTitle}
                        className="
                            w-16 h-24
                            sm:w-20 sm:h-28
                            object-cover
                            rounded-lg
                            shadow-md
                            transition-transform duration-300
                            group-hover:scale-105
                        "
                    />

                </div>

                {/* Book Details */}
                <div className="min-w-0 flex-1">

                    <h4 className="
                        text-base sm:text-lg
                        font-bold
                        text-gray-900
                        mb-1
                        truncate
                    ">
                        {loan.bookTitle}
                    </h4>

                    <p className="text-sm text-gray-500 mb-3 truncate">
                        by {loan.bookAuthor}
                    </p>

                    {/* Loan Information */}
                    <div className="flex flex-wrap items-center gap-2">

                        <div className="flex items-center gap-1.5 text-xs sm:text-sm text-gray-500">

                            <AccessTime sx={{ fontSize: 16 }} />

                            <span>
                                Due: {new Date(loan.dueDate).toLocaleDateString("en-IN")}
                            </span>

                        </div>

                        <GetStatusChip status={loan.status} />

                        <Chip
                            label={
                                `${days} days ${isOverdue ? "overdue" : "remaining"}`
                            }
                            size="small"
                            color={isOverdue ? "error" : "default"}
                            variant="outlined"
                        />

                    </div>

                </div>

            </div>

            {/* View Button */}
            <div className="flex-shrink-0 sm:self-center">

                <Button
                    variant="outlined"
                    size="small"
                    endIcon={<ArrowForward />}
                    sx={{
                        textTransform: "none",
                        fontWeight: 600,
                        borderRadius: 2,
                        px: 2,
                        color: "#4f46e5",
                        borderColor: "#c7d2fe",
                        "&:hover": {
                            backgroundColor: "#eef2ff",
                            borderColor: "#6366f1"
                        }
                    }}
                >
                    View Details
                </Button>

            </div>

        </div>
    );
};

export default CurrentLoanCard;
