(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 3] (maximum-non-nil [3 nil 1 2] 2)))
  (is (= [1 4 5] (maximum-non-nil '(nil 5 4 nil 1) 3)))
  (is (= [1 1 1 2] (maximum-non-nil (concat [2 nil] (repeat 1)) 4))))

(run-test test-variation)
