(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "Hello_World" (normalize-delimiters "Hello World")))
  (is (= "Hello-World" (normalize-delimiters "Hello  World")))
  (is (= "-A_B-C-" (normalize-delimiters "  A B   C  ")))
  (is (= nil (normalize-delimiters nil))))

(run-test test-variation)
