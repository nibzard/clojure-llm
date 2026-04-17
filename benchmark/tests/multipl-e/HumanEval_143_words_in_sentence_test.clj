(require '[clojure.test :refer [deftest is run-test]])

(def candidate words_in_sentence)

(deftest test-humaneval

  (is (= (candidate "This is a test") "is"))
  (is (= (candidate "lets go for swimming") "go for"))
  (is (= (candidate "there is no place available here") "there is no place"))
  (is (= (candidate "Hi I am Hussein") "Hi am Hussein"))
  (is (= (candidate "go for it") "go for it"))
  (is (= (candidate "here") ""))
  (is (= (candidate "here is") "is"))
)

(run-test test-humaneval)