(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (second-distinct-smallest [3 1 2 1 4])))
  (is (= nil (second-distinct-smallest '(5 5 5))))
  (is (= nil (second-distinct-smallest [])))
  (is (= nil (second-distinct-smallest nil))))

(run-test test-variation)
