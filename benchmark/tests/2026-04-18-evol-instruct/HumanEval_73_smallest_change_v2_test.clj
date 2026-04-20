(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (smallest_palindrome_edit-distance [1 2 3 5 4 7 9 6])))
  (is (= 1 (smallest_palindrome_edit-distance '(1 2 3 4 3 2 2))))
  (is (= 0 (smallest_palindrome_edit-distance nil)))
  (is (= 2 (smallest_palindrome_edit-distance (range 5)))))

(run-test test-variation)
