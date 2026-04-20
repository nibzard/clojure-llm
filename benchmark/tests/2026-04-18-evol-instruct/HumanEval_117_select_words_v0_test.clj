(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["cat" "bee" ""] (select_words ["cat" nil "horse" "bee" ""] 3)))
  (is (= [] (select_words [] 2)))
  (is (= ["one" "two"] (select_words [nil "one" "two" "three"] 3))))

(run-test test-variation)
