(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["A+" "B" "C" "E" "A-"]
         (numerical_letter_grade [4.0 3 1.7 nil 3.5])))
  (is (= ["A+" "A" "A-"]
         (numerical_letter_grade (take 3 (range 4.0 1.0 -1.0)))))
  (is (= []
         (numerical_letter_grade []))))

(run-test test-variation)
