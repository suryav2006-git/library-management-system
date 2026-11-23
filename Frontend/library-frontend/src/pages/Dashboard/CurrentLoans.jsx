
import CurrentLoanCard from "./CurrentLoanCard";



const loans = [
    {
        id: 1,
        bookTitle: "Lord of the Mysteries",
        bookCoverImage: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTntwC5dKySyWoWPfOQ3sI7T-hw3Bjo6-sxnChFemSm8w&s=10",
        bookAuthor: "Cuttlefish That Loves Diving",
        dueDate: "2026-11-29",
        status: "ACTIVE",
        remainingDays: 5,
        overdueDays: 0
    },
    {
        id: 2,
        bookTitle: "Omniscient Reader's Viewpoint",
        bookCoverImage: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxDpnpOeT7hxK4cieOpVGOrPXGwk0VDVQwG3Evjkhf8g&s=10",
        bookAuthor: "Sing Shong",
        dueDate: "2026-11-26",
        status: "ACTIVE",
        remainingDays: 2,
        overdueDays: 0
    },
    {
        id: 3,
        bookTitle: "The Wings of Fire",
        bookCoverImage: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQy4EIfcEUCN4jB16KdWG7DWAuopDBKzYGzDLkTKbfUdaqkXYrdERmJlyzg&s=10",
        bookAuthor: "A.P.J. Abdul Kalam",
        dueDate: "2026-11-20",
        status: "OVERDUE",
        remainingDays: -4,
        overdueDays: 4
    },
    {
        id: 4,
        bookTitle: "I'm Not a Regressor",
        bookCoverImage: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQXEcJk7j438DTX-6poKgkd4-X2YGhOHZ80239cqeMXQ&s=10",
        bookAuthor: "Ryu Seong",
        dueDate: "2026-12-02",
        status: "ACTIVE",
        remainingDays: 8,
        overdueDays: 0
    }
];


const CurrentLoans = () => {
    return (
        <div className="w-full">

            {/* Section Header */}
            <div className="flex flex-wrap items-center justify-between gap-2 mb-5">

                <div>
                    <h3 className="text-xl sm:text-2xl font-bold text-gray-900">
                        Books You're Currently Reading
                    </h3>

                    <p className="text-sm text-gray-500 mt-1">
                        Keep track of your borrowed books and due dates.
                    </p>
                </div>

                <span className="text-sm font-semibold text-indigo-600 bg-indigo-50 px-3 py-1.5 rounded-full">
                    {loans.length} Books
                </span>

            </div>

            {/* Book Cards */}
            <div className="flex flex-col gap-3">

                {loans.map((loan) => (
                    <CurrentLoanCard
                        loan={loan}
                        key={loan.id}
                    />
                ))}

            </div>

        </div>
    );
};

export default CurrentLoans;
