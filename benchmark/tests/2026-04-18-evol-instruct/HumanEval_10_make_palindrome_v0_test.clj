(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (make_palindrome [])))
  (is (= [:a :b :c :b :a] (make_palindrome [:a :b :c])))
  (is (= [1 2 3 2 1] (make_palindrome [1 2 3 2]))))

(run-test test-variation)
