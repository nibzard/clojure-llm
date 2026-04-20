(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:a "x" nil 1 2 3] (move-num [1 :a "x" 2 nil 3]))))

(run-test test-variation)
