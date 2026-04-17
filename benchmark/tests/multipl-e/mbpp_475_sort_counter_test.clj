(require '[clojure.test :refer [deftest is run-test]])

(def candidate sort_counter)

(deftest test-humaneval

  (is (= (candidate {"Math" 81 "Physics" 83 "Chemistry" 87}) [["Chemistry" 87] ["Physics" 83] ["Math" 81]]))
  (is (= (candidate {"Math" 400 "Physics" 300 "Chemistry" 250}) [["Math" 400] ["Physics" 300] ["Chemistry" 250]]))
  (is (= (candidate {"Math" 900 "Physics" 1000 "Chemistry" 1250}) [["Chemistry" 1250] ["Physics" 1000] ["Math" 900]]))
)

(run-test test-humaneval)