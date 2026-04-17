(require '[clojure.test :refer [deftest is run-test]])

(def candidate index_minimum)

(deftest test-humaneval

  (is (= (candidate [["Rash" 143] ["Manjeet" 200] ["Varsha" 100]]) "Varsha"))
  (is (= (candidate [["Yash" 185] ["Dawood" 125] ["Sanya" 175]]) "Dawood"))
  (is (= (candidate [["Sai" 345] ["Salman" 145] ["Ayesha" 96]]) "Ayesha"))
)

(run-test test-humaneval)