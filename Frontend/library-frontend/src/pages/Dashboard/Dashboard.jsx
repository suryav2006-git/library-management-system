import { statsConfig } from './StateConfig';
import StatesCard from './StatesCard';

const Dashboard = () => {

  const stateData = statsConfig({
      myLoans: [1,2,3],
      reservations: [1,2],
      stats: {readingStreak : 5}
  });

  return (
    <div className='min-h-screen bg-gradient-to-br from-indigo-50 via-white to 
    bg-purple-500 py-8'>
      <div className='max-w-7xl px-4 sm:px-6 lg:px-8'>

        <div className='mb-8 animate-fade-in-up'>
          
          <h1 className='text-4xl font-bold text-indigo-500 mb-2'>

            My {" "} <span className='bg-gradient-to-r from-indigo-600 to-purple-600
            bg-clip-text text-transparent' >
              Dashboard
            </span>
          </h1>

          <p className='text-lg text-gray-600 bgColor' >
            Track Your Reading Journey And Manage Your Library
          </p>

        </div>

        {/* State Card */}

        <div className = "grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          {
            stateData.map((item) => <StatesCard
                bgColor={item.bgColor}
                textColor = {item.textColor}
                icon={item.icon}
                value={item.value}
                title={item.title}
                subtitle={item.subtitle}
                key={item.id}
            />
          )
          }
        </div>


      </div>
    </div>
  );
}

export default Dashboard
