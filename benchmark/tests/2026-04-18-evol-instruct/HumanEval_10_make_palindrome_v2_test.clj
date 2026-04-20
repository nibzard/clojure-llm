(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (make_palindrome_vec [])))
  (is (= [:a :b :c :a] (make_palindrome_vec [:a :b :c])))
  (is (= [1 2 1] (make_palindrome_vec [1 2 1])))
  (is (= [1 2 3 4 3 2 1] (make_palindrome_vec [1 2 3 4]))))

(run-test test-variation)
