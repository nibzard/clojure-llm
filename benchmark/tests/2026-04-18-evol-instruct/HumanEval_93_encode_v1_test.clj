(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 4 3 8 2] (encode [1 2 3 4 nil 11])))
  (is (= '(5 12 7) (encode '(5 6 nil 7)))))

(run-test test-variation)
