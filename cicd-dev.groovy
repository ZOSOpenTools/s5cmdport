node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/ZOSOpenTools/s5cmdport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/ZOSOpenTools/s5cmdport.git'), string(name: 'PORT_DESCRIPTION', value: 'Parallel S3 and local filesystem execution tool.' ), string(name: 'BUILD_LINE', value: 'DEV') ]
  }
}
