(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '("clojure" "lisp" "fp")
         (parse-tags "Clojure, Lisp clojure; FP, lisp")))
  (is (= '()
         (parse-tags nil)))
  (is (= '("cats" "dogs" "birds")
         (parse-tags "  cats, dogs; cats  birds "))))

(run-test test-variation)
