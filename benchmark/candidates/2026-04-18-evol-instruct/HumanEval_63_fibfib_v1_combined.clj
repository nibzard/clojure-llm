(defn pal-stack
  "Return true if the given collection is a palindrome when read as a sequence of elements.

  The function must:
  - work on any sequential collection (lists, vectors, lazy seqs)
  - treat nil as an empty collection
  - return true for empty collections and single-element collections
  - compare elements with =

  Examples:
  >>> (pal-stack [1 2 3 2 1])
  true
  >>> (pal-stack '(a b b a))
  true
  >>> (pal-stack [1 2 3])
  false
  >>> (pal-stack nil)
  true"
  [xs]
  (let [s (seq xs)]
    (= s (reverse s))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (pal-stack [1 2 3 2 1])))
  (is (= true (pal-stack '(a b b a))))
  (is (= false (pal-stack [1 2 3])))
  (is (= true (pal-stack nil))))

(run-test test-variation)
