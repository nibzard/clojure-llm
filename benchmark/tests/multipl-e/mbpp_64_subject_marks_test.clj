(require '[clojure.test :refer [deftest is run-test]])

(def candidate subject_marks)

(deftest test-humaneval

  (is (= (candidate [["English" 88] ["Science" 90] ["Maths" 97] ["Social sciences" 82]]) [["Social sciences" 82] ["English" 88] ["Science" 90] ["Maths" 97]]))
  (is (= (candidate [["Telugu" 49] ["Hindhi" 54] ["Social" 33]]) [["Social" 33] ["Telugu" 49] ["Hindhi" 54]]))
  (is (= (candidate [["Physics" 96] ["Chemistry" 97] ["Biology" 45]]) [["Biology" 45] ["Physics" 96] ["Chemistry" 97]]))
)

(run-test test-humaneval)