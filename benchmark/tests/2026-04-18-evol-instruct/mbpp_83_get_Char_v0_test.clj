(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= \e (get-char ["ab" "C"])))
  (is (= \z (get-char [])))
  (is (= \n (get-char [nil "A"]))))

(run-test test-variation)
