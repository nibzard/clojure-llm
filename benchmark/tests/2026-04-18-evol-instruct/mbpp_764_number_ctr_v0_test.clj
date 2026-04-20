(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (count-numeric-elements [1 "2" nil 3.5 :x]))))

(run-test test-variation)
