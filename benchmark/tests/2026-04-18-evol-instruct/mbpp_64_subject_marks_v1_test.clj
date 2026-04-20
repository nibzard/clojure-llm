(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [["Math" 90] ["English" 90] ["Science" 85]]
         (subject_marks [["Math" 90] ["Science" 85] ["English" 90]]))))

(run-test test-variation)
