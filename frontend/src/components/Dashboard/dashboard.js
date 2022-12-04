import InmateNumbers from "./InmateNumbers/InmateNumbers";
import DashboardTabs from "./Tabs/tabs";

const Dashboard = () => (
  <>
    <div
      style={{
        display: "flex",
        flexDirection: "row",
        alignItems: "center",
      }}
    >
      <InmateNumbers />
    </div>
    <div
      style={{
        display: "flex",
        flexDirection: "row",
        alignItems: "center",
      }}
    >
      <DashboardTabs />
    </div>
  </>
);

export default Dashboard;
