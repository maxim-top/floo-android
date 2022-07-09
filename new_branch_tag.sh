ver=$1
git checkout master
git stash
git checkout -b $ver
git push --set-upstream origin $ver
git tag -a $ver -m '$ver'
git checkout -b 'v'$ver
mkdir app/src/main/jniLibs
cp -r app/build/intermediates/library_and_local_jars_jni/Openssl_1_1_1Release/jni/* app/src/main/jniLibs/
read -p "Comment app/build.gradle then press Enter: externalNativeBuild ndk flavorDimensions productFlavors externalNativeBuild android.libraryVariants.all "
git add app/src/main/jniLibs/
git add app/build.gradle
git commit -m 'new so'
git push --set-upstream origin 'v'$ver
git tag -a 'v'$ver -m 'v'$ver
git push --tags
git checkout master
git stash pop
