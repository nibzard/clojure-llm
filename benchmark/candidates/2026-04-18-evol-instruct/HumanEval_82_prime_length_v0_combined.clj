(defn palindromic-length?
  "Write a function that takes a collection and returns true if the collection's
  length is a palindrome number (reads the same forward and backward), or false otherwise.

  Count the number of elements using the collection itself, so it works for lists,
  vectors, and lazy sequences.

  Examples
  >>> (palindromic-length? [1 2 3 4 5])
  true
  >>> (palindromic-length? '(a b c d))
  false
  >>> (palindromic-length? (range 11))
  true
  >>> (palindromic-length? nil)
  false"
  [coll]
  (let [n (count coll)
        s (str n)]
    (= s (clojure.string/reverse s))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (palindromic-length? [1 2 3 4 5])))
  (is (= false (palindromic-length? '(a b c d))))
  (is (= true (palindromic-length? (range 11))))
  (is (= false (palindromic-length? nil))))

(run-test test-variation)
