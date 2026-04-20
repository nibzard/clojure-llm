(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["Hi" "my" "name" "is" "John"]
         (words_string ["Hi, my" nil ["name is" ["John"]]])))
  (is (= ["One" "two" "three" "four" "five"]
         (words_string [nil ["One, two" ["three, four"]] "five"]))))

(run-test test-variation)
