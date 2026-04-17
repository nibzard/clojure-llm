(require '[clojure.test :refer [deftest is run-test]])

(def candidate otherside_rightangle)

(deftest test-humaneval

  (is (= (candidate 7 8) 10.63014581273465))
  (is (= (candidate 3 4) 5))
  (is (= (candidate 7 15) 16.55294535724685))
)

(run-test test-humaneval)