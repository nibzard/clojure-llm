(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 4] (remove-nested [1 [2 3] 4])))
  (is (= [] (remove-nested nil)))
  (is (= [:b] (remove-nested '([:a] :b [c])))))

(run-test test-variation)
