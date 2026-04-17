(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_value)

(deftest test-humaneval

  (is (= (candidate {"Cierra Vega" 12 "Alden Cantrell" 12 "Kierra Gentry" 12 "Pierre Cox" 12} 10) false))
  (is (= (candidate {"Cierra Vega" 12 "Alden Cantrell" 12 "Kierra Gentry" 12 "Pierre Cox" 12} 12) true))
  (is (= (candidate {"Cierra Vega" 12 "Alden Cantrell" 12 "Kierra Gentry" 12 "Pierre Cox" 12} 5) false))
)

(run-test test-humaneval)