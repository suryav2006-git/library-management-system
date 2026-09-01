import CurrentLoanCard from "./CurrentLoanCard"

const loan = {
  bookTitle: "The Great Gatsby",
  bookCoverImage: "https://cdn.pixabay.com/photo/2020/01/30/17/49/book-4806076_1280.jpg",
  bookAuthor: "Mr Studies Helper",
  dueDate: "2026-08-01",
  status: "ACTIVE",
  remainingDays: 5,
  overdueDays: 0
}

const CurrentLoans = () => {
  return (
    <div className="p-6">

      <h3 className='text-2xl font-bold text-gray-900 mb-6'>
        Books You're Currently Reading
      </h3>

      <div className="space-y-4" >
        {/* List of Current Loans Will go Here */}

        {[1, 1, 1, 1].map((item, index) => <CurrentLoanCard
          loan={loan}
          key={index} />)}


      </div>


    </div>
  )
}

export default CurrentLoans
