(require '[clojure.test :refer [deftest is run-test]])

(def candidate encrypt)

(deftest test-humaneval

  (is (= (candidate "hi") "lm"))
  (is (= (candidate "asdfghjkl") "ewhjklnop"))
  (is (= (candidate "gf") "kj"))
  (is (= (candidate "et") "ix"))
  (is (= (candidate "faewfawefaewg") "jeiajeaijeiak"))
  (is (= (candidate "hellomyfriend") "lippsqcjvmirh"))
  (is (= (candidate "dxzdlmnilfuhmilufhlihufnmlimnufhlimnufhfucufh") "hbdhpqrmpjylqmpyjlpmlyjrqpmqryjlpmqryjljygyjl"))
  (is (= (candidate "a") "e"))
)

(run-test test-humaneval)