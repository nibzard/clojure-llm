(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 9 (max-nested [1 [4 nil] #{2 9} '(3 "x")])))
  (is (= nil (max-nested [nil :a []])))
  (is (= 42 (max-nested (lazy-seq [1 [2] #{42} "ignore"]))))

(run-test test-variation)
