(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 3 nil nil] (move-num [1 nil 2 nil 3])))
  (is (= [] (move-num nil)))
  (is (= [] (move-num []))))

(run-test test-variation)
