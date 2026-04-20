(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["<1>" "<2>" "<3>"]
         (doall (add-string "<%s>" [1 2 3]))))
  (is (= ["00" "01" "02"]
         (take 3 (add-string "%02d" (range)))))
  (is (= ()
         (add-string "%s" nil))))

(run-test test-variation)
