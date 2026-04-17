(require '[clojure.test :refer [deftest is run-test]])

(def candidate move_num)

(deftest test-humaneval

  (is (= (candidate "I1love143you55three3000thousand") "Iloveyouthreethousand1143553000"))
  (is (= (candidate "Avengers124Assemble") "AvengersAssemble124"))
  (is (= (candidate "Its11our12path13to14see15things16do17things") "Itsourpathtoseethingsdothings11121314151617"))
)

(run-test test-humaneval)