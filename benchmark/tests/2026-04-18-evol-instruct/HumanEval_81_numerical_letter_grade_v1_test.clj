(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:excellent :good :fair :pass :fail]
         (normalize-scores [95 82 70 61 40])))
  (is (= [] (normalize-scores nil)))
  (is (= [:good :good :good]
         (take 3 (normalize-scores (repeat 88))))))

(run-test test-variation)
