(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 3 3 3] (take 5 (rolling-max-transducer [1 2 3 2 3 4 2]))))
  (is (= [0 1 2 3] (take 4 (rolling-max-transducer (range)))))

(run-test test-variation)
