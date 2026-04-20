(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (lucas-prefix 0)))
  (is (= [2] (lucas-prefix 1)))
  (is (= [2 1 3 4 7] (lucas-prefix 5))))

(run-test test-variation)
