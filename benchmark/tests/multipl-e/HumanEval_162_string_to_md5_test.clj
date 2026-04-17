(require '[clojure.test :refer [deftest is run-test]])

(def candidate string_to_md5)

(deftest test-humaneval

  (is (= (candidate "Hello world") "3e25960a79dbc69b674cd4ec67a72c62"))
  (is (= (candidate "") nil))
  (is (= (candidate "A B C") "0ef78513b0cb8cef12743f5aeb35f888"))
  (is (= (candidate "password") "5f4dcc3b5aa765d61d8327deb882cf99"))
)

(run-test test-humaneval)